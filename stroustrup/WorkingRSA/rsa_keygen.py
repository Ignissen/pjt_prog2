import math
import random
import sys

if(len(sys.argv) != 3):
    print("use: python rsa_keygen.py <number to find prime1 around> <number to find prime2 around>")


def lcm(x, y):
   return abs(x * y) // math.gcd(x, y)

def isPrime(num):
    if(num < 2):
        return False
    sqrtnum = math.sqrt(num)
    for i in range(2, int(sqrtnum)):
        if(num % i == 0):
            return False
    return True


def genPrime(num):
    
    for i in range(num, 0, -1):
        if(isPrime(i)):
            return i

#print(genPrime(30))
            
p = genPrime(int(sys.argv[1]))
q = genPrime(int(sys.argv[2]))


N = p * q


phi_n = lcm(p - 1, q - 1)


e = 2



e = 0
for i in range(2, phi_n):
    if(math.gcd(i, phi_n) == 1):
        e = i
        break

d = 0
for i in range(100000000):
    if((i * e) % phi_n == 1):
        d = i
        break


f = open("pub.rsa", "w")
f.write(str(e))
f.close()
f = open(".priv.rsa", "w")
f.write(str(d))
f.close()
f = open(".n.rsa", "w")
f.write(str(N))
f.close()

