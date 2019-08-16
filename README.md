# training_springboot

- DB接続設定 application.properties は各々のローカルDBに合わせる。
- ローカルデータベース
    - データベース名: spring_test
    - テーブル名: employee

```
CREATE TABLE employee(`empno` bigint(20) NOT NULL AUTO_INCREMENT, `empname` varchar(255) DEFAULT NULL, PRIMARY KEY (`empno`)) DEFAULT CHARSET=utf8;
```
```
+-------+------------+
| empno | empname    |
+-------+------------+
|     1 | 従業員A    |
|     2 | 従業員B    |
|     3 | 従業員C    |
|     4 | 従業員D    |
+-------+------------+
```
