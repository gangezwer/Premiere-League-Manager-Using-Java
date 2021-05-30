import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GoalsortComponent } from './goalsort.component';

describe('GoalsortComponent', () => {
  let component: GoalsortComponent;
  let fixture: ComponentFixture<GoalsortComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GoalsortComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GoalsortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
