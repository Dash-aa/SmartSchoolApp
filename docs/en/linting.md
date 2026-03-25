# Linting in the SmartSchool Project

## Selected Linter and Rationale

In the SmartSchool project, the static code analysis tool **Checkstyle** is used.

Checkstyle was chosen because it:
- is well-suited for Java projects;
- integrates with Maven;
- enforces a consistent coding style;
- helps detect common issues;
- improves code readability and maintainability.

Additionally, the project uses:
- **SpotBugs** — to detect potential bugs in the code;
- **Spotless** — for automatic code formatting.

---

## Basic Rules and Their Explanation

As part of the lab work, a configuration file **checkstyle.xml** was created.

Main rules:

- **UnusedImports** — disallows unused imports;
- **AvoidStarImport** — prohibits the use of `*` in imports;
- **NeedBraces** — enforces the use of curly braces;
- **WhitespaceAfter / NoWhitespaceBefore** — controls spacing;
- **RedundantImport** — prevents duplicate imports;
- **EmptyBlock** — disallows empty blocks;
- **EqualsHashCode** — ensures consistency between equals() and hashCode();
- **HiddenField** — prevents field hiding by parameters;
- **LineLength** — limits line length (120 characters);
- **FileTabCharacter** — disallows tab characters.

These rules ensure consistent code style and quality.

---

## Ignored Directories

The following directories are excluded from checks:
- `target/` — generated files;
- `.idea/` — IDE-specific files.

---

## How to Run the Linter

Main command:
mvn checkstyle:check

Generate a report:
mvn checkstyle:checkstyl
