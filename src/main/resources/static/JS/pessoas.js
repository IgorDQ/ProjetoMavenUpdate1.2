$(document).ready(function () {

    $('#btnPesquisar').on('click', function () {

        const logradouro = $('#logradouro').val().trim();

        $('#mensagem').hide().text('');
        $('#tabelaResultados').addClass('d-none');
        $('#corpoTabela').empty();

        if (logradouro.length < 5) {
            $('#mensagem')
                .removeClass()
                .addClass('alert alert-warning')
                .text('Digite pelo menos 5 caracteres')
                .show();
            return;
        }

        $.ajax({
            url: '/pessoas/listar',
            method: 'GET',
            data: { logradouro: logradouro },

            success: function (dados) {

                if (!dados || dados.length === 0) {
                    $('#mensagem')
                        .removeClass()
                        .addClass('alert alert-info')
                        .text('Nenhuma pessoa encontrada')
                        .show();
                    return;
                }

                let tbody = $('#corpoTabela');

                dados.forEach(function (pessoa) {
                    tbody.append(`
                        <tr>
                            <td>${pessoa.nome}</td>
                            <td>${pessoa.idade}</td>
                            <td>${pessoa.cidade}</td>
                            <td>${pessoa.estado}</td>
                        </tr>
                    `);
                });

                $('#tabelaResultados').removeClass('d-none');
            },

            error: function (xhr) {

                let msg = 'Erro no sistema.';


                if (xhr.responseJSON && xhr.responseJSON.erro) {
                    msg = xhr.responseJSON.erro;
                }

                else if (xhr.responseText) {
                    msg = xhr.responseText;
                }

                $('#mensagem')
                    .removeClass()
                    .addClass('alert alert-danger')
                    .text(msg)
                    .show();
            }
        });
    });
});
