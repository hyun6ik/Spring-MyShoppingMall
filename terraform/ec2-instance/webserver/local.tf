locals {
  region = var.region

  ec2_name = format("%s-ec2", var.name)
  ssh_sg_name = format("%s-ssh-sg", var.name)
  http_sg_name = format("%s-http-sg", var.name)
  http_8080_sg_name = format("%s-http-8080-sg", var.name)
  https_sg_name = format("%s-https-sg", var.name)
  role_name    = format("%s-role", var.name)


  tags = merge(var.tags, { Owner = var.owner, Environment = var.env })

  vpc_id            = data.terraform_remote_state.vpc.outputs.vpc_id
  public_subnet_ids = data.terraform_remote_state.vpc.outputs.public_subnet_ids
  azs               = data.terraform_remote_state.vpc.outputs.azs
  default_sg_id     = data.terraform_remote_state.vpc.outputs.default_security_group_id

  ami_id        = data.aws_ami.this.id
  ami_owners    = var.ami_owners
  ami_filters   = var.ami_filters
  instance_type = var.instance_type
  key_name      = var.key_name

  ssh_sg_description      = var.ssh_sg_description
  ssh_ingress_cidr_blocks = var.ssh_ingress_cidr_blocks
  ssh_ingress_rules       = var.ssh_ingress_rules
  ssh_egress_rules        = var.ssh_egress_rules

  http_sg_description = var.http_sg_description
  http_ingress_cidr_blocks = var.http_ingress_cidr_blocks
  http_ingress_rules = var.http_ingress_rules
  http_egress_rules = var.http_egress_rules

  http_tcp_listeners = var.http_tcp_listeners
  http_tcp_listener_rules = var.http_tcp_listener_rules

  https_sg_description = var.https_sg_description
  https_ingress_cidr_blocks = var.https_ingress_cidr_blocks
  https_ingress_rules = var.https_ingress_rules
  https_egress_rules = var.https_egress_rules

  https_tcp_listeners = var.https_tcp_listeners
  https_tcp_listener_rules = var.https_tcp_listener_rules

  http_8080_sg_description = var.http_8080_sg_description
  http_8080_ingress_cidr_blocks = var.http_8080_ingress_cidr_blocks
  http_8080_ingress_rules = var.http_8080_ingress_rules
  http_8080_egress_rules = var.http_8080_egress_rules

  http_8080_tcp_listeners = var.http_8080_tcp_listeners
  http_8080_tcp_listener_rules = var.http_8080_tcp_listener_rules

  custom_role_policy_arns = var.custom_role_policy_arns
  trusted_role_services   = var.trusted_role_services
}
