# PDF generating by itext and pdfbox
## How to run
- run PdfTest
- see itext.pdf itext2.pdf and pdfbox.pdf
## itext
- Layout support html
- Html can be provided FreeMarker
- Header and footer by page event
## itext2
- Simplest itext without freemarker
## pdfbox
- Layout all by absolute position
- Long text should be calculated to start a new line.
- Each time you start a new line should be surrounded with beginText() and endText()
- Better to use Courier New (equal width for each char).
