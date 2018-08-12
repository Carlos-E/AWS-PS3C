package clases;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DB extends DynamoDBMapper {

	private final static String AccessKeyID = "AKIAJSINT4F7K5BSGDRA";
	private final static String SecretKey = "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";
	
	private static Regions region = Regions.US_EAST_1;

	private static BasicAWSCredentials basicCreds = new BasicAWSCredentials(AccessKeyID, SecretKey);
	private static AWSStaticCredentialsProvider staticCreds = new AWSStaticCredentialsProvider(basicCreds);

	public DB() {
		super(AmazonDynamoDBClientBuilder.standard().withRegion(region).withCredentials(staticCreds).build());
	}

}
