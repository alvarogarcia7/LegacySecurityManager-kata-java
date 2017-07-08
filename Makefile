.PHONY: build
build:
	mvn package

.PHONY: clean
clean:
	mvn clean

.PHONY: test
test:
	java -cp "./target/classes" Main < testcases/1.input > /tmp/1.actual
	diff /tmp/1.actual testcases/1.expected
	echo "Tests are OK"


