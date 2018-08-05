package clases;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DB {

	private final String AccessKeyID = "AKIAJSINT4F7K5BSGDRA";
	private final String SecretKey = "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";

	private BasicAWSCredentials awsCreds = new BasicAWSCredentials(AccessKeyID, SecretKey);

	private DynamoDBMapper mapper;

	public DB() {
		mapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build());
	}

	public DynamoDBMapper getMapper() {
		return mapper;
	}

}
