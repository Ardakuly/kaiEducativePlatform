package spring.educativeprojects.kaieducativeplatform.services.datajpa;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
public class AwsService {


    @Value("${application.bucket.name}")
    private String BUCKET;

    private AmazonS3 s3Client;

    @Autowired
    public AwsService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadVideo(MultipartFile video) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy|hh:mm:ss");
        String name = formatter.format(now) + video.getOriginalFilename();
        File convertedFile = multipartFileToFile(video);
        s3Client.putObject(new PutObjectRequest(BUCKET, name,convertedFile));
        convertedFile.delete();
        return name;
    }

    public byte[] readVideoFromAws(String videoName) {

        S3Object s3Object = s3Client.getObject(BUCKET, videoName);

        S3ObjectInputStream inputStream = s3Object.getObjectContent();

        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException exception) {
            log.error("Error converting to byte array");
        }

        return null;
    }
    
    
    public String deleteVideo (String videoName) {
        s3Client.deleteObject(BUCKET, videoName);

        return videoName + " removed .....";
    }

    private File multipartFileToFile(MultipartFile video) {

        File convertedFile = new File(video.getOriginalFilename());

        try (FileOutputStream fot = new FileOutputStream(convertedFile)){
            fot.write(video.getBytes());
        } catch (IOException exception) {
            log.error("Error converting MultipartFile to File");
        }

        return convertedFile;

    }
}
