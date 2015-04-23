var Request = Request || {};
(function(exports) {
    function load() {

        $('body').on('click', '#btnCSV', function() {
            var $btn = $(this).button('loading');
            $.ajax({
                url: '/csv',
                type: 'get',
                success: function(data) {
                    console.log(data);
                    $( "#result").html(data);
                    $( "#result").show();
                    $btn.button('reset');
                },
                error: function() {
                    alert('error!');
                    $btn.button('reset');
                }
            });
        });

        $( "#result" ).hide();

    }
    exports.load = load;
})(Request);

