# Обчислювальна система

Програма для вирiшення задачі MA = min(Z) * MX + max(Z) * (MR * MC) * d в паралельній комп’ютерній системі зі спільною
пам’яттю.

## Стек технологій

* **Мова:** Java
* **Механізми обчислень:** Multithreading, Concurrency

## Розподіл введення та виведення даних:

* **T1** - введення MC, Z
* **T2** - введення MX
* **T3** - виведення MA
* **T4** - введення MR, d

## Схема взаємодії потоків

<p>
    <img src="images/schema.png" width="600" style="background-color: white;" />
</p>

## Як запустити проєкт

1. Клонуйте репозиторій  
   `git clone https://github.com/andrfurs/computing-system.git`
2. Скомпілюйте проєкт  
   `javac Main.java`
3. Запустіть програму  
   `java Main`

## Приклад виконання програми

На 1 ядрі:  
![test1.png](images/test1.png)

На 4 ядрах:  
![test4.png](images/test4.png)

Коефіцієнт прискорення K = 1,749