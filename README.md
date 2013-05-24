About
=============
DeprotectPDF is a short Java project around the iPDF library, in its 5.4.1 version.
Both are distributed under the GNU Affero General Public License.

How to build
============

With command-line Ant: 
```
ant package-for-store
```
How to run
============

Through the command-line interface:
```
java -jar store/DeprotectPDF.jar input.pdf
```

The `input.deprotected.pdf` file will be created. If it already existed, it will be erased.
