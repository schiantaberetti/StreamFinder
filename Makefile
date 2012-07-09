
all:
	jar cmf MANIFEST.MF StreamFinder.jar lib org -C bin . 
	
clean:
	rm -R bin
	rm $JAR_FILE
