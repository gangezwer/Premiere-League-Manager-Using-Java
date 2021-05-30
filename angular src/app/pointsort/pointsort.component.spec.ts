import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PointsortComponent } from './pointsort.component';

describe('PointsortComponent', () => {
  let component: PointsortComponent;
  let fixture: ComponentFixture<PointsortComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PointsortComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PointsortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
