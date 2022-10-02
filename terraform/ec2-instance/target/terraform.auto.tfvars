env   = "dev"
name  = "shoppingmall"
owner = "hyun6ik"
tags  = {}

# AMI
ami_owners = ["099720109477"]
ami_filters = [
  {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-focal-20.04-amd64-server-*"]
  },
  {
    name   = "virtualization-type"
    values = ["hvm"]
  }
]

# EC2
instance_type = "t3.medium"
key_name      = "dev"

# ssh sg
ssh_sg_description      = "SSH Security group for Bastion EC2 instance"
ssh_ingress_cidr_blocks = ["0.0.0.0/0"]
ssh_ingress_rules       = ["ssh-tcp"]
ssh_egress_rules        = ["all-all"]

# http sg
http_sg_description = "HTTP Security group for Bastion EC2 instance"
http_ingress_cidr_blocks = ["0.0.0.0/0"]
http_ingress_rules = ["http-80-tcp"]
http_egress_rules = ["all-all"]

http_tcp_listeners = [
  {
    port = 80
    protocol = "HTTP"
    action_type = "fixed-response"
    fixed_response = {
      content_type = "text/plain"
      message_body = "Not Found"
      status_code = "403"
    }
  }
]

http_tcp_listener_rules = [
  {
    http_listener_index = 0
    actions = [{
      type = "forward"
      target_group_index = 0
    }]
    conditions = [{
      path_patterns = ["/*"]
    }]
  }
]

https_sg_description = "HTTPS Security group for Bastion EC2 instance"
https_ingress_cidr_blocks = ["0.0.0.0/0"]
https_ingress_rules = ["https-443-tcp"]
https_egress_rules = ["all-all"]

https_tcp_listeners = [
  {
    port = 443
    protocol = "HTTPS"
    action_type = "fixed-response"
    fixed_response = {
      content_type = "text/plain"
      message_body = "Not Found"
      status_code = "403"
    }
  }
]

https_tcp_listener_rules = [
  {
    https_listener_index = 0
    actions = [{
      type = "forward"
      target_group_index = 0
    }]
    conditions = [{
      path_patterns = ["/*"]
    }]
  }
]

trusted_role_services = ["ec2.amazonaws.com"]
custom_role_policy_arns = [
  "arn:aws:iam::aws:policy/AmazonS3FullAccess",
]
