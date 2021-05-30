import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatchsortComponent } from './matchsort.component';

describe('MatchsortComponent', () => {
  let component: MatchsortComponent;
  let fixture: ComponentFixture<MatchsortComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatchsortComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MatchsortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
