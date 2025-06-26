package model;

public sealed interface User permits User.Student, User.Faculty {
    String getName();
    String getId();

    record Student(String name, String id, String course, int year) implements User {
        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getId() {
            return id;
        }
    }

    record Faculty(String name, String id, String department) implements User {
        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getId() {
            return id;
        }
    }
}
