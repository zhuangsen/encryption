#密码分类
##时间
	"古典密码"：以字符为基本加密单元
	"现代密码"：以信息块为基本加密单元
##保密内容算法

	受限制算法: 算法的保密性基于保持算法的秘密	,应用于军事领域属于古典密码	   				    
	基于密钥算法： 算法的保密性基于对密钥的保密	,属于现代 现代密码
                                        			  
##密码体制
###对称密码
	单钥密码或私钥密码，指加密密钥与解密密钥相同
DES(3DES):
![Alt text](1488004615635.png)
AES、PBE、IDEA
###非对称密码			
	双钥密码或公钥密码			指加密密钥与解密密钥不同，密钥分公钥、私钥。
###对称密码算法		
	单钥密码算法或私钥密码算法   	指应用于对称密码的加密、解密算法
###非对称密码算法		
	双钥密码算法或公钥密码算法		指应用于非对称密码的加密、解密算法


##明文处理方法
###分组密码
	指加密时将名为分成固定长度的组，用同一密钥和算法对每一块加密，输出也是固定长度的密文。多用于网络加密。
###流密码
	也称序列密码。指加密时对每次加密一位或者一个字节明文。

###散列函数
	验证数据的完整性，特点：长度不受限制，哈希值容易计算，散列运算过程不可逆；
	算法
	MD(消息摘要算法，128位摘要信息)：MD2(JDK)、MD4(Bouncy Castle)、MD5(JDK)
	SHA(安全散列算法，固定长度信息，可以被伪造)：SHA-1(摘要长度：160)、SHA-2(SHA-224、SHA-256、SHA-384、SHA-512)
	MAC(消息认证算法，兼容了MD和SHA两种特性，并加入了密钥)：
		--MD系列：HmacMD2、HmacMD4、HmacMD5
		--SHA系列：HmacSHA1、HmacSHA224、HmacSHA256、HmacSHA384、HmacSHA512
		其他：RipeMD、Tiger、Whirlpool、GOST3411(都是Bouncy Castle实现)
###数字签名
	主要是针对以数字的形式存储的消息进行的处理