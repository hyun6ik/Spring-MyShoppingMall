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

variable "http_sg_description" {}
variable "http_ingress_cidr_blocks" {}
variable "http_ingress_rules" {}
variable "http_egress_rules" {}
variable "http_tcp_listeners" {}
variable "http_tcp_listener_rules" {}

variable "https_sg_description" {}
variable "https_ingress_cidr_blocks" {}
variable "https_ingress_rules" {}
variable "https_egress_rules" {}
variable "https_tcp_listeners" {}
variable "https_tcp_listener_rules" {}

variable "custom_role_policy_arns" {}
variable "trusted_role_services" {}
