export interface PageableDTO<T> {
  content: T[];
  pageNumber: number;
  pageSize: number;
  totalPages: number;
}

