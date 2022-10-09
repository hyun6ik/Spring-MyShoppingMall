env   = "dev"
name  = "redis"
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
instance_type = "t2.micro"
key_name      = "dev"

# ssh sg
ssh_sg_description      = "SSH Security group for Bastion EC2 instance"
ssh_ingress_cidr_blocks = ["0.0.0.0/0"]
ssh_ingress_rules       = ["ssh-tcp"]
ssh_egress_rules        = ["all-all"]

# http sg
redis_sg_description = "Redis group for Bastion EC2 instance"
redis_ingress_cidr_blocks = ["0.0.0.0/0"]
redis_ingress_rules = ["redis-tcp"]
redis_egress_rules = ["all-all"]

redis_tcp_listeners = [
  {
    port = 6379
    protocol = "HTTP"
    action_type = "fixed-response"
    fixed_response = {
      content_type = "text/plain"
      message_body = "Not Found"
      status_code = "403"
    }
  }
]

redis_tcp_listener_rules = [
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
