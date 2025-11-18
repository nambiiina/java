# README – MySQL + phpMyAdmin sans réseau Docker

## Prérequis
Docker 20.10+  
Linux : ajoutez votre utilisateur au groupe `docker` ou préfixez les commandes par `sudo`.

---

## 1. Run MySQL
```bash
docker run -d \
  --name mysql8 \
  -e MYSQL_ROOT_PASSWORD=rootpass \
  -e MYSQL_DATABASE=patients_db \
  -e MYSQL_USER=springuser \
  -e MYSQL_PASSWORD=springpass \
  -p 3306:3306 \
  mysql:8.0
```

## 2. Run phpMyAdmin
### a. Windows/macOS
```bash
docker run -d \
  --name phpmyadmin \
  -e PMA_HOST=host.docker.internal \
  -e PMA_PORT=3306 \
  -p 8080:80 \
  phpmyadmin/phpmyadmin
```
### b. Linux (host-gateway)
```bash
docker run -d \
  --name phpmyadmin \
  --add-host=host.docker.internal:host-gateway \
  -e PMA_HOST=host.docker.internal \
  -e PMA_PORT=3306 \
  -p 8080:80 \
  phpmyadmin/phpmyadmin
```
**Interface** : http://localhost:8080 \
**Identifiants** : springuser / springpass
**Config ufw** : sudo ufw allow 3306/tcp

## 3. Connexion application
url jdbc : `jdbc:mysql://localhost:3306/patients_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC` \
user/pass : `springuser/springpass`

## 4. Clean
```bash
docker stop mysql8 phpmyadmin
docker rm mysql8 phpmyadmin
```

## Pourquoi pas de réseau?
Tous les ports sont publiés sur l’hôte ; `host.docker.internal` remplace le DNS interne. \
Plus rapide, parfait pour un usage local ou test.