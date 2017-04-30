# 密码分类

## 时间

	"古典密码"：以字符为基本加密单元
	"现代密码"：以信息块为基本加密单元

## 保密内容算法

	受限制算法: 算法的保密性基于保持算法的秘密	,应用于军事领域属于古典密码	   				    
	基于密钥算法： 算法的保密性基于对密钥的保密	,属于现代 现代密码

## 密码体制

	### 对称密码
	
		单钥密码或私钥密码，指加密密钥与解密密钥相同
		
	### 非对称密码
	
		双钥密码或公钥密码，指加密密钥与解密密钥不同，密钥分公钥、私钥。
		
	### 对称密码算法
	
		单钥密码算法或私钥密码算法，指应用于对称密码的加密、解密算法
		
		DES:数据加密标准，已被破解，不推荐使用：1、柯克霍夫原则；2、安全问题
		
		3DES:3重DES：密钥长度增强，迭代次数提高，效率比较低
		
		AES:是目前使用最多的对称加密算法，至今尚未被破解，通常用于移动通信系统加密以及基于SSH协议的软件,DES替代者
		
		PBE(Password Based Encryption):结合了消息摘要算法和对称加密算法的优点，基于口令加密
		
		IDEA
		
	### 非对称密码算法
	
		双钥密码算法或公钥密码算法，指应用于非对称密码的加密、解密算法
		
		DH——密钥交换算法
		
		RSA——基于因子分解:数据加密&数字签名
		
		ElGamal——基于离散对数:由Bouncy Castle提供，公钥加密，私钥解密
		
		ECC——椭圆曲线加密


## 明文处理方法

	### 分组密码
	
	指加密时将名为分成固定长度的组，用同一密钥和算法对每一块加密，输出也是固定长度的密文。多用于网络加密。
	
	### 流密码
	
	也称序列密码。指加密时对每次加密一位或者一个字节明文。
	

## 消息摘要算法 散列函数
	
	验证数据的完整性，特点：长度不受限制，哈希值容易计算，散列运算过程不可逆；
	
	算法:
	
	MD(消息摘要算法，128位摘要信息)：MD2(JDK)、MD4(Bouncy Castle)、MD5(JDK)
	
	SHA(安全散列算法，固定长度信息，可以被伪造)：SHA-1(摘要长度：160)、SHA-2(SHA-224、SHA-256、SHA-384、SHA-512)
	
	MAC(消息认证算法，兼容了MD和SHA两种特性，并加入了密钥)：
	
		--MD系列：HmacMD2、HmacMD4、HmacMD5
		
		--SHA系列：HmacSHA1、HmacSHA224、HmacSHA256、HmacSHA384、HmacSHA512
		
		其他：RipeMD、Tiger、Whirlpool、GOST3411(都是Bouncy Castle实现)
		
## 数字签名
	
	私钥签名、公钥验证
	
	主要是针对以数字的形式存储的消息进行的处理
	
	包括：DSA、ECDSA
	
注：Java安全组成:

JCA(Java Cryptography Architecture)

JCE(Java Cryptography Extension)

JSSE(Java Secure Socket Extension)

JAAS(Java Authentication and Authentication Service)

使用第三方的加解密提供者：

1、调用Security类的addProvider or insertProviderAt method

2、jdk1.8.0_51\jre\lib\security\java.security里面security.provider

相关Java包、类：

java.security-消息摘要

javax.crypto-安全消息摘要，消息认证(鉴别)码

java.net.ssl-安全套接字

第三方扩展：

Bouncy Castle-两种支持方案:1)调用；2)配置

Commons Codec
	
	-Apache
	
	-Base64、二进制、十六进制、字符集编码
	
	-Url编码/解码