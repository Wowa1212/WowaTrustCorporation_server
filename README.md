# Wowa Trust Corporation

**Wowa Trust Corporation** je webová aplikace vytvořená v jazyce **Java** a **Spring Boot**, která umožňuje správu pojištěnců (Insured). 
Uživatelé mohou přidávat nové pojištěnce, upravovat existující záznamy, mazat pojištěnce a zobrazovat jejich seznam.

## Technologie

### Backend
- **Java 17**: Programovací jazyk použitý pro implementaci serverové logiky.
- **Spring Boot**: Framework pro vývoj robustních a škálovatelných backend aplikací.
  - **Spring MVC**: Zpracovává HTTP požadavky a odpovědi.
  - **Spring Data JPA**: Umožňuje snadnou práci s databázemi.
  - **MariaDB**: Databázový systém používaný k ukládání záznamů pojištěnců.
- **Hibernate**: ORM framework pro mapování Java tříd na databázové tabulky.
- **REST API**: Pro komunikaci mezi frontendem a backendem pomocí HTTP.

### Frontend
- **HTML5**: Struktura webové aplikace.
- **CSS3**: Pro styling a layout uživatelského rozhraní.
- **JavaScript (ES6)**: Dynamické funkce aplikace (např. AJAX požadavky na API).
- **Fetch API**: Pro volání REST API backendu.

### Další technologie
- **GitHub**: Pro správu verzí a sdílení kódu.
- **SSH**: Pro bezpečné připojení a nasazení na server.
- **cURL**: Testování API pomocí příkazového řádku.

---

## Funkce aplikace

### 1. Přidání pojištěnce
- Uživatel může přidat nového pojištěnce vyplněním formuláře.
- Pojištěnec je uložen do databáze přes API volání `/api/insureds/add`.

### 2. Aktualizace pojištěnce
- Uživatel může upravit údaje existujícího pojištěnce (např. změnit adresu nebo telefonní číslo).
- Aktualizované údaje jsou uloženy přes API volání `/api/insureds/update/{id}`.

### 3. Smazání pojištěnce
- Uživatel může odstranit pojištěnce podle jeho ID.
- API volání `/api/insureds/remove/{id}` odstraní odpovídající záznam z databáze.

### 4. Zobrazení seznamu pojištěnců
- Aplikace umožňuje zobrazit seznam všech pojištěnců uložených v databázi.
- API volání `/api/insureds/list` vrací seznam pojištěnců v JSON formátu, který se vykreslí v HTML tabulce.

### 5. Vyhledání pojištěnce podle ID
- Uživatel může vyhledat konkrétního pojištěnce pomocí jeho ID.
- API volání `/api/insureds/find/{id}` vrací informace o zadaném pojištěnci.

---

## Instalace a spuštění

### 1. Požadavky
- **Java 17+**
- **Maven 3+**
- **MariaDB**

### 2. Nastavení databáze
1. Spusťte MariaDB server.
2. Vytvořte databázi `wowatrustcorporation`:
   ```sql
   CREATE DATABASE wowatrustcorporation CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

Konfigurace aplikace

spring.datasource.url=jdbc:mariadb://<DB_HOST>:<DB_PORT>/wowatrustcorporation
spring.datasource.username=<DB_USERNAME>
spring.datasource.password=<DB_PASSWORD>
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

Spuštění aplikace

nohup java -jar WowaTrustCorporation-1.0.jar > app.log 2>&1 &
 
