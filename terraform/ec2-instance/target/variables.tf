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

variable "http_8080_sg_description" {}
variable "http_8080_ingress_cidr_blocks" {}
variable "http_8080_ingress_rules" {}
variable "http_8080_egress_rules" {}
variable "http_8080_tcp_listeners" {}
variable "http_8080_tcp_listener_rules" {}

variable "custom_role_policy_arns" {}
variable "trusted_role_services" {}
