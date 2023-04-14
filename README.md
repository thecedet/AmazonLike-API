# Installation

## MariaDB

```bash
$ sudo apt install mariadb-client mariadb-server
```

### Création de la database

```bash
$ sudo mysql -u root
```
```sql
MariaDB [(none)]> CREATE DATABASE AmazonLike;
MariaDB [(none)]> CREATE USER "amazonlike"@localhost IDENTIFIED BY "admin";
MariaDB [(none)]> GRANT ALL PRIVILEGES ON AmazonLike.* TO amazonlike@localhost;
MariaDB [(none)]> flush privileges;
```

## Maven

```bash
sudo apt-get install maven
```

## 

# Démarrer le projet

```bash
$ sudo service mariadb start
```

```bash
$ mvn install
$ mvn spring-boot:run
 ```

 # Documentation

 ## Utilisateurs : /users

| **Methode**|**Path**|**Body variable**|**Auth**|
|:-:|-|-|:-:|
|POST|/signin|- (String) username <br> - (String) password|no|
|POST|/signup|no|no|
|GET|/roles|no|ROLE_ADMIN|
|GET|/|no|ROLE_ADMIN|
|DELETE|/{username}|no|ROLE_ADMIN|
|GET|/{username}|no|ROLE_ADMIN|
|GET|/me|no|yes|
|GET|/refresh|no|yes|
|PUT|/update/{username}| - (String) username <br> - (String) email <br> - (String) lastName <br> - (String) fristName <br> - (List Role)|ROLE_ADMIN|
|PUT|/update/me|- (String) username <br> - (String) email <br> - (String) lastName <br> - (String) fristName <br> - (List Role)|yes|
|PUT|/update/me/password| - (String) password | yes|

 ## Produits : /products

| **Methode**|**Path**|**Body variable**|**Auth**|
|:-:|-|-|:-:|
|GET|/|no|no|
|GET|/{id}|no|no|
|POST|/|- (String) name <br> - (String) shortDescription <br> - (String) description <br> - (float) price <br> - (List category) categories|ROLE_ADMIN|
|PUT|/{id}|- (String) name <br> - (String) shortDescription <br> - (String) description <br> - (float) price <br> - (List category) categories|ROLE_ADMIN|
|GET|/categories|no|no|
|DELETE|/{id}|no|ROLE_ADMIN|