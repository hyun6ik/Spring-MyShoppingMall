module "ec2" {
  source = "terraform-aws-modules/ec2-instance/aws"

  name = local.ec2_name

  ami                         = local.ami_id
  key_name                    = local.key_name
  instance_type               = local.instance_type
  availability_zone           = element(local.azs, 0)
  subnet_id                   = element(local.public_subnet_ids, 0)
  vpc_security_group_ids      = [module.ssh.security_group_id,module.http.security_group_id, module.https.security_group_id, module.http_8080.security_group_id, local.default_sg_id]
  associate_public_ip_address = true

  user_data  = data.template_file.userdata.rendered
  tags = local.tags
}

module "ssh" {
  source  = "terraform-aws-modules/security-group/aws"
  version = "~> 4.0"

  name        = local.ssh_sg_name
  description = local.ssh_sg_description
  vpc_id      = local.vpc_id

  ingress_cidr_blocks = local.ssh_ingress_cidr_blocks
  ingress_rules       = local.ssh_ingress_rules
  egress_rules        = local.ssh_egress_rules

  tags = local.tags
}
module "http" {
  source  = "terraform-aws-modules/security-group/aws"
  version = "~> 4.0"

  name        = local.http_sg_name
  description = local.http_sg_description
  vpc_id      = local.vpc_id

  ingress_cidr_blocks = local.http_ingress_cidr_blocks
  ingress_rules       = local.http_ingress_rules
  egress_rules        = local.http_egress_rules

  tags = local.tags
}

module "https" {
  source  = "terraform-aws-modules/security-group/aws"
  version = "~> 4.0"

  name        = local.https_sg_name
  description = local.https_sg_description
  vpc_id      = local.vpc_id

  ingress_cidr_blocks = local.https_ingress_cidr_blocks
  ingress_rules       = local.https_ingress_rules
  egress_rules        = local.https_egress_rules

  tags = local.tags
}
module "http_8080" {
  source  = "terraform-aws-modules/security-group/aws"
  version = "~> 4.0"

  name        = local.http_8080_sg_name
  description = local.http_8080_sg_description
  vpc_id      = local.vpc_id

  ingress_cidr_blocks = local.http_8080_ingress_cidr_blocks
  ingress_rules       = local.http_8080_ingress_rules
  egress_rules        = local.http_8080_egress_rules

  tags = local.tags
}
