# Smart School

## Що реалізовано:
- Головна сторінка з оголошеннями (R1.2) + базова палітра/назва (R1.1)
- Реєстрація з вибором ролі та валідацією (R1.10, R1.13)
- Вхід з перевіркою хешу паролю (BCrypt) (R1.10)
- Особистий кабінет (R1.3) + обов'язкова автентифікація (R2.2)
- Захист закритого розділу редиректом на /login (R1.16)
- Logout (R1.11)
- Контакти (/about) (R1.15)
- Дані зберігаються централізовано в in-memory сховищі (для лабораторної) (R1.8)

## Демо-акаунти (seed):
- admin / Admin!1234 (ADMIN)
- teacher_math / Teacher!1234 (TEACHER)
- student1 / Student!1234 (STUDENT)

## Документація/Documentation

### 🇺🇦 Українською
- [Архітектура](docs/uk/architecture.md)
- [Бізнес-логіка](docs/uk/business_logic.md)
- [Взаємодія компонентів](docs/uk/component_interaction.md)
- [Генерація документації](docs/uk/generate_docs.md)
- [Linting](docs/uk/linting.md)
- [Test-Driven Documentation](docs/uk/test_driven_docs.md)

### 🇬🇧 English
- [Architecture](docs/en/architecture.md)
- [Business Logic](docs/en/business_logic.md)
- [Component Interaction](docs/en/component_interaction.md)
- [Documentation Generation](docs/en/generate_docs.md)
- [Linting](docs/en/linting.md)
- [Test-Driven Documentation](docs/en/test_driven_docs.md)

## Документування коду

У проєкті SmartSchool для документування коду використовується стандарт **Javadoc**.

Що потрібно документувати:
- усі публічні класи;
- усі публічні методи;
- сервіси та контролери;
- DTO-класи, які використовуються для передачі даних;
- методи з нетривіальною логікою.

Правила документування:
- перед класами і методами використовувати коментарі у форматі `/** ... */`;
- коротко описувати призначення класу або методу;
- для параметрів використовувати `@param`;
- для значень, що повертаються, використовувати `@return`;
- якщо метод може викидати винятки, використовувати `@throws`.

Документація має оновлюватися разом зі змінами в коді.

## Онлайн-документація

Актуальна документація проєкту доступна через GitHub Pages.
Вона автоматично оновлюється після змін у гілці `main`.
![Docs](https://dash-aa.github.io/SmartSchoolApp/)

## Getting Started (Developer Guide)
Ця інструкція дозволяє новому розробнику запустити проєкт з нуля.

**Вимоги:**
Перед початком переконайтесь, що встановлено:
- Java JDK 21
- Maven
- Git
- IDE (IntelliJ IDEA / VS Code)

**Клонування репозиторію:**
git clone https://github.com/Dash-aa/SmartSchoolApp.git
cd SmartSchoolApp

**Встановлення залежностей**
mvn clean install

**Запуск проєкту**
mvn spring-boot:run
Або запустіть клас SmartSchoolApplication з IDE.

**Доступ до застосунку**
Після запуску відкрийте у браузері:
http://localhost:8080

**Основні команди**
Збірка проєкту: mvn clean package
Запуск тестів: mvn test
Повна перевірка: mvn clean verify

## Scripts

Для спрощення запуску доступні скрипти:

### Windows
- run-dev.bat — запуск у режимі розробки
- run-prod.bat — запуск у production

### Linux / Mac
- run-dev.sh
- run-prod.sh