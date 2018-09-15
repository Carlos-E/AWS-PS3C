var table;

$(document).ready(function() {
  let url = '/read/all' + window.location.search;

  $.ajax({
    url: url,
    type: 'POST',
    dataType: 'json'
  })
    .done(function(response) {
      loadTable(response);
    })
    .fail(function(xhr, status, errorThrown) {
      alert('Error cargando informacion');
      console.log('Failed Request To Servlet /listarEnvios envios');
    });
});

function loadTable(list) {
  
  let columns = [];
  let data = list[0];
  for (let propName in data) {
    if (data.hasOwnProperty(propName)) {
      let propValue = data[propName];
      columns.push({ title: propName });
    }
  }

  let dataSet = [];
  list.forEach(element => {
    let row = [];

    columns.forEach(column => {
      row.push(element[column.title]);
    });

    dataSet.push(row);
  });

  $('#toggleColumn').append('Filtro');
  columns.forEach(function(currentValue, index, array) {
    $('#toggleColumn').append(
      ' - <a style="color:rgb(213, 108, 62) !important;font-size:0.7rem;cursor: pointer;" class="toggle-vis" data-column="' +
        index +
        '">' +
        currentValue.title.toUpperCase() +
        '</a>'
    );
  });

  $('a.toggle-vis').on('click', function(e) {
    e.preventDefault();
    let column = table.column($(this).attr('data-column'));
    column.visible(!column.visible());
  });
  
  table = $('#table').DataTable({
    data: dataSet,
    language: {
      url: '//cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json'
    },
    columns: columns,
    search: {
      search: getParameterByName('search') != null ? getParameterByName('search') : ''
    },
    initComplete: function() {}
  });
}
