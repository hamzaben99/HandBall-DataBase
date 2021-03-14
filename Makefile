CLASSPATH=/home/oracle/app/oracle/product/11.2.0/dbhome_1/jdbc/lib/ojdbc5.jar:.:${BUILD_DIR}:CLASSPATH

SRC_DIR=./src
BUILD_DIR=./build
HTML_DIR=./html

all : build

build: clean main

run:
	java -cp $(CLASSPATH) Main

main: $(SRC_DIR)/*.java
	mkdir $(BUILD_DIR)
	javac -d $(BUILD_DIR) -cp $(CLASSPATH) $(SRC_DIR)/*.java

clean:
	rm -rf $(HTML_DIR) $(BUILD_DIR)