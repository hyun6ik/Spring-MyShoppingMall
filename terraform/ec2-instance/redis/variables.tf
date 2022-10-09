variable "env" {}
variable "name" {}
variable "owner" {}

variable "ami_owners" {}
variable "ami_filters" {}
variable "instance_type" {}
variable "key_name" {}

variable "ssh_sg_description" {}
variable "ssh_ingress_cidr_blocks" {}
variable "ssh_ingress_rules" {}
variable "ssh_egress_rules" {}

variable "redis_sg_description" {}
variable "redis_ingress_cidr_blocks" {}
variable "redis_ingress_rules" {}
variable "redis_egress_rules" {}
variable "redis_tcp_listeners" {}
variable "redis_tcp_listener_rules" {}

