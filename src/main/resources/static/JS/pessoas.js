$(document).ready(function () {

    $('#btnPesquisar').click(function () {

        const logradouro = $('#logradouro').val();

        // Limpa mensagens e tabela
        $('#mensagem').hide();
        $('#tabelaPessoas').hide();
        $('#tabelaPessoas tbody').empty();

        // REGRA DE NEGÓCIO NO FRONT (UX)
        if (logradouro.length < 5) {
            $('#mensagem')
                .removeClass()
                .addClass('alert alert-warning')
                .text('numero minimo de caracteres(5)')
                .show();
            return;
        }

        $.ajax({
            url: 'http://localhost:8080/pessoas/listar',
            method: 'GET',
            data: {
                logradouro: logradouro
            },
            success: function (data) {

                if (data.length === 0) {
                    $('#mensagem')
                        .removeClass()
                        .addClass('alert alert-info')
                        .text('Informação não encontrada')
                        .show();
                    return;
                }

                let tbody = $('#tabelaPessoas tbody');

                data.forEach(function (pessoa) {
                    tbody.append(
                        `<tr>
                            <td>${pessoa.nome}</td>
                            <td>${pessoa.idade}</td>
                            <td>${pessoa.cidade}</td>
                            <td>${pessoa.estado}</td>
                        </tr>`
                    );
                });

                $('#tabelaPessoas').show();
            },
            error: function (xhr) {

                if (xhr.status === 400) {
                    $('#mensagem')
                        .removeClass()
                        .addClass('alert alert-warning')
                        .text(xhr.responseText)
                        .show();
                } else {
                    $('#mensagem')
                        .removeClass()
                        .addClass('alert alert-danger')
                        .text('Erro no sistema')
                        .show();
                }
            }
        });

    });

});
