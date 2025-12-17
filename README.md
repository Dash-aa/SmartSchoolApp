# Smart School (expanded v2, auth improved, no tests)

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

## Запуск:
Run `SmartSchoolApplication` і відкрий http://localhost:8080
