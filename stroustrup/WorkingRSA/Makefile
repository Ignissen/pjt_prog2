all:
	python3 rsa_keygen.py 600 1200
	python3 rsa.py clean.txt -e .n.rsa pub.rsa encrypted.txt

decrypt:
	python3 rsa.py encrypted.txt -d .n.rsa .priv.rsa decrypted.txt
clean:
	rm -f encrypted.txt decrypted.txt .n.rsa pub.rsa .priv.rsa