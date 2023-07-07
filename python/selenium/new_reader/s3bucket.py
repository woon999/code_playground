import boto3

s3 = boto3.resource('s3')  # s3에 대한 권한 및 상태를 s3(변수)에 저장
for bucket in s3.buckets.all():
    print(bucket)