# Spring Batch – Technical Test

Hi! 👋 
This project was built as part of a coding test, where I had to choose two Spring Batch exercises and implement them in a simple and working way.

I picked exercises that helped me explore two important patterns in Spring Batch: conditional flows and chunk-based processing.

---

What’s inside?

I implemented two jobs:

1. A **conditional job** that runs different steps depending on the time of day
2. A **stateless chunk job** that processes a static list of strings

Both jobs are designed to be as clear and beginner-friendly as possible, since I’m still getting comfortable with Spring Batch.

---

Exercise 1 – Conditional Job (Morning/Evening)

- This job uses a custom decider (`MorningEveningDecider`)
- It checks the current time when the job starts:
  - If it's before noon → it runs a step called `loadStep`
  - If it's afternoon or later → it runs `validateStep`
- Then, it continues with both steps no matter what
- Steps are simple tasklets that just print messages to the console

Main file: `ExecutionFlowJobConfig.java`

---

Exercise 2 – Stateless Chunk Job

- This job reads a static list of words: `["Alpha", "Bravo", "Charlie", "Delta", "Echo"]`
- Each item is processed with a 200ms pause and tagged with a `"Processed-"` prefix
- The results are printed to the console
- I used `chunk(1)` just to keep things simple and readable

Main file: `Exercice2JobConfig.java`

---

How to run it

By default, the job `exercise2Job` runs automatically when you start the app.

Entry point: `BatchtestApplication.java`

If you want to try the other job (`executionFlowJob`), you can switch the injected job in that main class.

---

Tech used

- Java 17
- Spring Boot 3.2.5
- Spring Batch
- H2 (in-memory database)

---
