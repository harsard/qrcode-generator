
Java 17 , Springboot 3.2.1
This application about 
QR Code generator and Logging correlation using micrometer

```curl --location --request POST 'http://localhost:8081/api/qrcodegeneration' \
--header 'Content-Type: application/json' \
--data-raw '{"qrEmbedableUrl": "https://www.google.com"}'
```
### screen shot for QR CODE request and response
![img.png](img.png)

### screen shot for micrometr correlation ID
![img_1.png](img_1.png)
