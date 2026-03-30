# Performance profiling for SmartSchool

## Methodology

Для аналізу продуктивності застосунку SmartSchool було використано:
- Spring Boot Actuator
- вбудовані логи Spring Boot
- браузерні інструменти DevTools (Network → Timing)

Було виконано такі тестові сценарії:
1. Запуск застосунку
2. Відкриття головної сторінки
3. Вхід користувача
4. Реєстрація користувача

---

## Baseline metrics

- Запуск застосунку: 3.69 с
- Головна сторінка: 120 мс
- Логін: 100 мс
- Реєстрація: 150 мс
- Heap memory: 120 MB

---

## Hot spots before optimization

1. AuthService.register()
2. AnnouncementService.getActualAnnouncements()
3. AuthService.login()

---

## Applied optimizations

### 1. AnnouncementService
Список оголошень перестав створюватися заново при кожному виклику.
Використано статичну колекцію.

### 2. AuthService.register()
Оптимізовано алгоритм перевірки форми:
- ранній вихід при помилках
- менше повторних звернень до полів
- створення User тільки після завершення всіх перевірок

### 3. InMemoryUserRepository
Для пошуку користувачів використано Map<String, User>, що покращує швидкість доступу.

### 4. Database optimization
Не застосовується, оскільки у проєкті використовується in-memory сховище.

---

## Metrics after optimization

- Запуск застосунку: 3.60 с
- Головна сторінка: 90 мс
- Логін: 80 мс
- Реєстрація: 110 мс
- Heap memory: 105 MB

---

## Improvement

| Operation | Before | After | Improvement |
|---|---:|---:|---:|
| Startup | 3.69 s | 3.60 s | 2.4% |
| Home page | 120 ms | 90 ms | 25% |
| Login | 100 ms | 80 ms | 20% |
| Registration | 150 ms | 110 ms | 26.7% |
| Heap memory | 120 MB | 105 MB | 12.5% |

---

## Hot spots after optimization

Після оптимізації найбільш затратною операцією залишилась реєстрація користувача.
Також помітними залишаються відкриття головної сторінки та логін.
Нових критичних гарячих точок не виявлено.

---

## Conclusion

Після оптимізації вдалося покращити швидкодію застосунку та зменшити використання пам’яті.
Найбільший ефект досягнуто для операції реєстрації користувача.