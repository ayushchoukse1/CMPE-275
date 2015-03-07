# CMPE-275
Cmpe275-work

SUBMITTED BY:
 
 AYUSH CHOUKSE - 009981803 - ayush.choukse@sjsu.edu
-APOORVA GUPTA - 010001043 - apoorva.gupta@sjsu.edu
-
 
 How to run the project: Run the TestCase.java to execute all the 8 test cases from 1 to 8. 
 
+GOAL- implement access control; i.e. the shareFile, unshareFile method, and readFile . 
+The task was to use AOP to
+enforce the following access control, as well as adding logging.
+
+TestCases to check the Correctness of the service.
+
+1. testA: Bob cannot read Alice’s file /home/Alice/shared/alicetext1.txt.
+
+2. testB: Alice shares her file /home/Alice/shared/alicetext1.txt with Bob, and Bob
+can read Alice’s file.
+
+3. testC: Alice shares her file /home/Alice/shared/alicetext1.txt with Bob, and Bob
+can read Alice’s file, and Bob shares Alice’s file with Carl, and Carl can read Alice’s
+file.
+
+4. testD: Alice shares her file with Bob; Bob shares Carl’s file with Alice and gets an
+exception.
+
+5. testE: Alice shares her file with Bob, Bob shares Alice’s file with Carl, Alice unshares
+her file with Carl, and Carl cannot read Alice’s file.
+
+6. testF: Alice shares her file with Bob, Alice shared her file with Carl, Carl shares
+Alice’s file with Bob, Alice unshares her file with Bob, and Bob cannot read Alice’s
+file.
+
+7. testG:Alice shares her file with Bob, Bob shares Alice’s file with Carl, and Alice
+unshares her file with Bob. Bob shares Alice’s file with Carl with again, and gets an
+exception. Carl still has access to Alice’s file.
+
+8. testH:Alice shares her file1 with Bob, Bob tries to access Alice’s file2 and gets an
+exception.
