import sys
import csv

#print(len(sys.argv))
if(len(sys.argv) != 6):
    print("Use: python3 rsa.py <file to encrypt/decrypt> <-e/-d> <file of N> <file of private/public key> <output file>")
    sys.exit(1)

def encrypt(string, N, e):
    encrypted = []
    i = 0
    for c in string:
        encrypted.append(pow(c + i, e) % N)
        #print(chr(pow(ord(c), e) % N))
        i += 1
    return encrypted

def decrypt(string, N, d):
    decrypted = []
    i = 0
    for c in string:
        decrypted.append(pow(c, d) % N - i)
        i += 1
    return decrypted


if(sys.argv[2] == "-e"):
    f = open(sys.argv[5], "w")
    fin = open(sys.argv[1], "r").read()
    str_in = []
    for c in fin:
        str_in.append(ord(c))
    encrypted = encrypt(str_in, int(open(sys.argv[3]).read()), int(open(sys.argv[4]).read()))
    str_out = ""
    for x in encrypted:
        str_out += str(x) + "\t"
    f.write(str_out)
    f.close()
elif(sys.argv[2] == "-d"):
    fin = open(sys.argv[1]).read().split(sep="\t")
    fin.pop()
    
    str_in = []
    for x in fin:
        str_in.append(int(x))
    
    
    decrypted = decrypt(str_in, int(open(sys.argv[3]).read()), int(open(sys.argv[4]).read()))
    
    str_out = ""
    for x in decrypted:
        str_out += chr(x)

    f = open(sys.argv[5], "w")
    f.write(str_out)
    f.close()
else:
    print("Use: python3 rsa.py <file to encrypt/decrypt> <-e/-d> <file of N> <file of private/public key> <output file>")

