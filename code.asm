LDC 1
STV 101
LDC 2
STV 102
LDC 3
STV 103
LDC 0
STV 104
LDC 11
STV 201
LDC 12
STV 202
LDC 0
STV 203 END INIT
LDC 0
STV ZERO
LDC 101 aka x
STV CX
LDC 201 aka y
STV CY
LDC 301 aka z
STV CZ
LDIV CX
STIV CZ
EQL ZERO
JMN 34
LDC 1
ADD CX
STV CX
LDC 1
ADD CZ
STV CZ
JMP 23 -> LDIV
LDIV CY
STIV CZ
EQL ZERO
JMN 45
LDC 1
ADD CY
STV CY
LDC 1
ADD CZ
STV CZ
JMP 34 -> LDIV
HALT