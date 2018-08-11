<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="tab-pane fade" id="list-category" role="tabpanel"
	aria-labelledby="list-category-list">
	<div class="row">
		<div class="col-sm-6">
			<div class="card">
				<div class="card-header">
					<h4>Register Category</h4>
				</div>
				<div class="card-body">
					<form id="frm-category" class="needs-validation" novalidate>
						<input type="hidden" id="txt-category-no" name="category_no" value="">
						<div class="form-group row">
							<label class="col-sm-3 col-form-label" for="txt-category-title">Title</label>
							<div class="col-sm-9">
								<input class="form-control" type="text" id="txt-category-title"
									name="category_title" placeholder="Enter title"
									required="required">
							</div>
							<div class="valid-feedback">체크완료</div>
							<div class="invalid-feedback">invalid</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-3 col-form-label" for="txt-category-color">Color</label>
							<div class="col-sm-9">
								<input class="form-control" type="color" id="txt-category-color"
									name="category_color">
							</div>
							<div class="valid-feedback">체크완료</div>
							<div class="invalid-feedback">invalid</div>
						</div>
						<div class="text-right">
							<button type="button" id="btn-category-new"
								class="btn btn-outline-success">신규</button>
							<button type="button" id="btn-category-update"
								class="btn btn-outline-info">저장</button>
							<button type="button" id="btn-category-delete"
								class="btn btn-outline-danger">삭제</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="col-sm-6">
			<div class="table-responsive-sm">
				<h4>Category List</h4>
				<table id="tbl-category" class="table table-hover">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>색상</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
	</div>
</div>