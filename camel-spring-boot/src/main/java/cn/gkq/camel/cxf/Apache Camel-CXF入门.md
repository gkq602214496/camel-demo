# WebService

# 一.概述

CXF是一个开源的webservice框架，提供了对 JAX-WS 全面的支持，支持多种协议：SOAP、XML/HTTP、RESTful HTTP 等，可实现webservice服务的发布和使用。

## 1.1 webservice

WebService直译网络服务，是RPC的一样实现方式，WebService使用WSDL描述和使用SOAP协议传输的异构系统解决方案，WebService拥有三种基本的元素: SOAP、WSDL 以及 UDDI，采用Http + XML。

### 1.1.1WSDL文件详解

WSDL定义：用来描述WS和说明如何与WS通信的XML语言，即基于XML的语言的,用于描述Webservice及其方法、参数和返回值的一种语言，服务交互所需的所有细节都位于其WSDL文件中。

![image-20210521092606758](https://i.loli.net/2021/05/21/Y9tUQ6R3bXxg7Dw.png)

WSDL文件根标签是definitions，targetNamespace属性对应的值来自我们的包结构，是以倒置包名的形式呈现，name属性值是在发布服务在WS中的名称：服务名称=服务类名称+Service，WSDL文档包含两部分（接口、实现类）。

#### ①、WebService实现类部分：

binding元素:包含N个operation,每个都详细定义。

1)   binding节点：  name属性代表绑定的名称，由服务类名+PortBinding组成；

2)   soap：binding子元素的transport属性定义了wsdl文件的访问类型遵循SOAP规范，style属性定义了整个WSDL文件的描述或书写方式，默认为Document。

3)   operation子元素代表服务类提供服务的名称，有多个服务方法时会有operation子元素。这里的operation下的input个output并未描述需要传递和返回的参数类型。soap：body的use属性可以为literal，encoded。encoded和literal编码方式的差别在于参数类型是否出现在生成的Soap消息中，常用为literal，意为不对参数类型进行编码。

service元素:WS绑定地址，有port子元素：

1)  service节点：name属性代表服务的名称，和definitions的name必须相同，由服务类名+Service组成。

2)  port子元素定义了使用服务所要访问的端口信息，name属性由服务类名+Port组成，

3)  soap:address子元素定义了WS发布地址（一个基于http协议的地址）。

#### ②、WebService接口部分：

1)  portType:相当于类，里面包含N个operation，相当于N个方法

2) message:相当于参数或返回值

3) types元素：该元素内容就是Scehema文档。



### 1.1.2 SOAP详解

SOAP作为一个基于XML语言的协议用于有网上传输数据。

SOAP = 在HTTP的基础上+XML数据。
SOAP是基于HTTP的。

Envelope – 必须的部分。以XML的根元素出现。
Headers – 可选的(主要由程序员实现)。
Body – 必须。在body部分，包含要执行的服务器的方法。和发送到服务器的数据。