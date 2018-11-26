(function () {
    window.lodopPrinter = function () {
        var sf = this;
        
        sf.utils = {
            print: function (printer) {
                printer.PRINT();
            },
            printA: function(printer) {
                printer.PRINTA();
            },
            preview: function (printer) {
                printer.PREVIEW();
            }
        }
    }
})();