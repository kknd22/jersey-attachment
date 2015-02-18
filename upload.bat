curl -X POST -F "file=@input.docx" -F "file2=@input2.txt" http://localhost:8080/jersey-attachment-1.0/form/upload

REM curl -X POST -F "file=@input.rtf" -F "file2=@input2.txt" http://localhost:8080/jersey-attachment-1.0/form/upload -x http://localhost:8888

REM curl -X POST -F "file=@input.rtf" -F "file2=@input2.txt" http://localhost:8080/jersey-attachment-1.0/form/upload --proxy localhost:8888
169.254.73.120