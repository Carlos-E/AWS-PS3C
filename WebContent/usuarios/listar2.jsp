<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.logica.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="clases.*"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="com.logica.*"%>
<%@ page import="clases.*"%>
<%
	if (session.getAttribute("rol") == null) {
		//response.sendError(400, "Acceso incorrecto"); //cambiar
		response.sendRedirect("/error.jsp");
	}
	session.setAttribute("pagina", "Listar Usuarios");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>
	<%
		out.print(session.getAttribute("pagina").toString());
	%>
</title>
<jsp:include page="/head.jsp" />
</head>
<body>
	<!-- INICIO -->
	<div class="container-fluid" id="wrapper">
		<div class="row">
			<!-- INICIO NAVBAR -->
			<jsp:include page="/navbar.jsp" />
			<!--  ./NAVBAR -->
		</div>
	</div>
	<main class="col-xs-12 col-sm-8 col-lg-9 col-xl-10 pt-3 pl-4 ml-auto"> <!--  HEADER --> <jsp:include page="/header.jsp" /> <!--  ./HEADER --> <section class="row">
	<div class="col-md-12 col-lg-12">
		<div class="card mb-4">
			<!-- INICIO CONTAINER -->

			<div class="card-block" id="buscar-form">
				<h3 class="card-title">
					<%
						out.print(session.getAttribute("pagina").toString());
					%>
				</h3>

				<%
					ArrayList<usuario> listaUsuario = ControladorBD.escanearTabla("usuarios");
				%>
				<table class="table table-striped table-bordered dataTable">
					<thead>
						<tr>
							<th>Cedula</th>
							<th>Nombre</th>
							<th>Apellido</th>
							<th>Rol</th>
							<th>Correo</th>
							<th>Direccion</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < listaUsuario.size(); i++) {
						%>
						<tr>
							<td>
								<strong>
									<%
										out.println(listaUsuario.get(i).getUsuario());
									%>
								</strong>
							</td>
							<td>
								<%
									out.println(listaUsuario.get(i).getNombre());
								%>
							</td>
							<td>
								<%
									out.println(listaUsuario.get(i).getApellido());
								%>
							</td>
							<td>
								<%
									out.println(listaUsuario.get(i).getRol());
								%>
							</td>
							<td>
								<%
									out.println(listaUsuario.get(i).getCorreo());
								%>
							</td>
							<td>
								<%
									out.println(listaUsuario.get(i).getDireccion());
								%>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
		<div class="card mb-4">
			<div class="card-block">
				<h3 class="card-title">Data Table</h3>
				<h6 class="text-muted mb-4">
					Using
					<a href="https://datatables.net">DataTables plugin</a>
				</h6>

				<div id="example_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4">
					<div class="row">
						<div class="col-sm-12 col-md-6">
							<div class="dataTables_length" id="example_length">
								<label>
									Show
									<select name="example_length" aria-controls="example" class="form-control form-control-sm">
										<option value="10">10</option>
										<option value="25">25</option>
										<option value="50">50</option>
										<option value="100">100</option>
									</select>
									entries
								</label>
							</div>
						</div>
						<div class="col-sm-12 col-md-6">
							<div id="example_filter" class="dataTables_filter">
								<label>
									Search:
									<input type="search" class="form-control form-control-sm" placeholder="" aria-controls="example">
								</label>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<table id="example" class="table table-striped table-bordered dataTable" cellspacing="0" width="100%" role="grid" aria-describedby="example_info" style="width: 100%;">
								<thead>
									<tr role="row">
										<th class="sorting_asc" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending" style="width: 60px;">Name</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending" style="width: 79px;">Position</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending" style="width: 56px;">Office</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending" style="width: 30px;">Age</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending" style="width: 64px;">Start date</th>
										<th class="sorting" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" style="width: 63px;">Salary</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th rowspan="1" colspan="1">Name</th>
										<th rowspan="1" colspan="1">Position</th>
										<th rowspan="1" colspan="1">Office</th>
										<th rowspan="1" colspan="1">Age</th>
										<th rowspan="1" colspan="1">Start date</th>
										<th rowspan="1" colspan="1">Salary</th>
									</tr>
								</tfoot>
								<tbody>

























































									<tr role="row" class="odd">
										<td class="sorting_1">Airi Satou</td>
										<td>Accountant</td>
										<td>Tokyo</td>
										<td>33</td>
										<td>2008/11/28</td>
										<td>$162,700</td>
									</tr>
									<tr role="row" class="even">
										<td class="sorting_1">Angelica Ramos</td>
										<td>Chief Executive Officer (CEO)</td>
										<td>London</td>
										<td>47</td>
										<td>2009/10/09</td>
										<td>$1,200,000</td>
									</tr>
									<tr role="row" class="odd">
										<td class="sorting_1">Ashton Cox</td>
										<td>Junior Technical Author</td>
										<td>San Francisco</td>
										<td>66</td>
										<td>2009/01/12</td>
										<td>$86,000</td>
									</tr>
									<tr role="row" class="even">
										<td class="sorting_1">Bradley Greer</td>
										<td>Software Engineer</td>
										<td>London</td>
										<td>41</td>
										<td>2012/10/13</td>
										<td>$132,000</td>
									</tr>
									<tr role="row" class="odd">
										<td class="sorting_1">Brenden Wagner</td>
										<td>Software Engineer</td>
										<td>San Francisco</td>
										<td>28</td>
										<td>2011/06/07</td>
										<td>$206,850</td>
									</tr>
									<tr role="row" class="even">
										<td class="sorting_1">Brielle Williamson</td>
										<td>Integration Specialist</td>
										<td>New York</td>
										<td>61</td>
										<td>2012/12/02</td>
										<td>$372,000</td>
									</tr>
									<tr role="row" class="odd">
										<td class="sorting_1">Bruno Nash</td>
										<td>Software Engineer</td>
										<td>London</td>
										<td>38</td>
										<td>2011/05/03</td>
										<td>$163,500</td>
									</tr>
									<tr role="row" class="even">
										<td class="sorting_1">Caesar Vance</td>
										<td>Pre-Sales Support</td>
										<td>New York</td>
										<td>21</td>
										<td>2011/12/12</td>
										<td>$106,450</td>
									</tr>
									<tr role="row" class="odd">
										<td class="sorting_1">Cara Stevens</td>
										<td>Sales Assistant</td>
										<td>New York</td>
										<td>46</td>
										<td>2011/12/06</td>
										<td>$145,600</td>
									</tr>
									<tr role="row" class="even">
										<td class="sorting_1">Cedric Kelly</td>
										<td>Senior Javascript Developer</td>
										<td>Edinburgh</td>
										<td>22</td>
										<td>2012/03/29</td>
										<td>$433,060</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12 col-md-5">
							<div class="dataTables_info" id="example_info" role="status" aria-live="polite">Showing 1 to 10 of 57 entries</div>
						</div>
						<div class="col-sm-12 col-md-7">
							<div class="dataTables_paginate paging_simple_numbers" id="example_paginate">
								<ul class="pagination">
									<li class="paginate_button page-item previous disabled" id="example_previous">
										<a href="#" aria-controls="example" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
									</li>
									<li class="paginate_button page-item active">
										<a href="#" aria-controls="example" data-dt-idx="1" tabindex="0" class="page-link">1</a>
									</li>
									<li class="paginate_button page-item ">
										<a href="#" aria-controls="example" data-dt-idx="2" tabindex="0" class="page-link">2</a>
									</li>
									<li class="paginate_button page-item ">
										<a href="#" aria-controls="example" data-dt-idx="3" tabindex="0" class="page-link">3</a>
									</li>
									<li class="paginate_button page-item ">
										<a href="#" aria-controls="example" data-dt-idx="4" tabindex="0" class="page-link">4</a>
									</li>
									<li class="paginate_button page-item ">
										<a href="#" aria-controls="example" data-dt-idx="5" tabindex="0" class="page-link">5</a>
									</li>
									<li class="paginate_button page-item ">
										<a href="#" aria-controls="example" data-dt-idx="6" tabindex="0" class="page-link">6</a>
									</li>
									<li class="paginate_button page-item next" id="example_next">
										<a href="#" aria-controls="example" data-dt-idx="7" tabindex="0" class="page-link">Next</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /FIN CONTAINER -->

	</div>
	</section> </main>
	<!-- Modal -->
	<!--  FOOTER CON SCRIPTS -->
	<jsp:include page="/footer.jsp" />
	<!-- /FIN -->
</body>
</html>