import { Component, Input } from '@angular/core';

export type TableColumnType = 'text' | 'boolean' | 'date';

export interface TableColumn {
  key: string;
  label: string;
  type?: TableColumnType;
}

@Component({
  selector: 'app-view-all',
  templateUrl: './view-all.html',
  styleUrl: './view-all.css',
})
export class ViewAll {
  @Input() title = '';
  @Input() columns: TableColumn[] = [];
  @Input() data: readonly unknown[] = [];

  getCellValue(row: unknown, column: TableColumn): string {
    const value = this.readNestedValue(row, column.key);

    if (value === null || value === undefined || value === '') {
      return '—';
    }

    if (column.type === 'boolean') {
      return value === true ? 'Sì' : 'No';
    }

    if (column.type === 'date' && typeof value === 'string') {
      const date = new Date(value);
      return Number.isNaN(date.getTime()) ? value : date.toLocaleDateString('it-IT');
    }

    return String(value);
  }

  trackRow(row: unknown, index: number): string | number {
    if (row !== null && typeof row === 'object') {
      const id = (row as Record<string, unknown>)['id'];
      if (typeof id === 'string' || typeof id === 'number') {
        return id;
      }
    }

    return index;
  }

  private readNestedValue(row: unknown, path: string): unknown {
    let current = row;

    for (const key of path.split('.')) {
      if (current === null || typeof current !== 'object') {
        return null;
      }

      current = (current as Record<string, unknown>)[key];
    }

    return current;
  }
}
