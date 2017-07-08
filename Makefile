.PHONY: build
build:
	mvn package

.PHONY: clean
clean:
	mvn clean

.PHONY: test
test:
	echo "Unit and Acceptance tests"
	mvn test

	echo "End to end tests"
	java -jar ./target/*jar < testcases/1.input > /tmp/1.actual
	diff /tmp/1.actual testcases/1.expected
	echo "Tests are OK"

.PHONY: install
install:
	mvn install -DskipTests=true -Dmaven.javadoc.skip=true -B -V

