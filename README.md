## 社区

Github第三方登录流程
![img.png](https://github.com/wwmmrr/community/edit/master/img.png)

## 资料
[Spring 文档](https://spring.io/guides)

[Spring Web 文档](https://spring.io/guides/gs/serving-web-content)

[Bootstrap3 文档](https://v3.bootcss.com/getting-started/)

[Gitee OAuth文档](https://gitee.com/api/v5/oauth_doc#/)

[Gitee API文档,获取授权用户的信息](https://gitee.com/api/v5/swagger#/getV5User)


## 工具
https://git-scm.com/download

## 脚本

```sql
CREATE TABLE `table_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `account_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NULL DEFAULT NULL,
  `gmt_modified` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
```
