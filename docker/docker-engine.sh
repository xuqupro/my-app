yum install -y centos-release-xen
echo includepkgs=kernel kernel-firmware >> /etc/yum.repos.d/CentOS-Xen.repo
yum update -y kernel
reboot
curl -O -sSL https://get.docker.com/rpm/1.7.0/centos-6/RPMS/x86_64/docker-engine-1.7.0-1.el6.x86_64.rpm
yum localinstall --nogpgcheck docker-engine-1.7.0-1.el6.x86_64.rpm
service docker start

vim /etc/yum.repos.d/virt7-docker-common-release.repo
 
[virt7-docker-common-release]
name=virt7-docker-common-release
baseurl=http://cbs.centos.org/repos/virt7-docker-common-release/x86_64/os/
gpgcheck=0