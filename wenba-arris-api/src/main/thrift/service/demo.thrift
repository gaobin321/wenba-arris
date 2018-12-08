include "../dto/demoDto.thrift"
namespace java com.wenba.template.service.demo
namespace php com.wenba.template.php.service.demo

service HelloWorldService{
string sayHello(1:demoDto.Hello user)
}
