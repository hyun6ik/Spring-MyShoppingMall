terraform {
  backend "s3" {
    bucket = "hyun6ik-shoppingmall-backend"
    key = "ec2/master-db/terraform.tfstate"
    region = "ap-northeast-2"
    max_retries = 3
  }
}
