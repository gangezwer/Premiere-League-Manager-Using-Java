import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RandommatchComponent } from './randommatch.component';

describe('RandommatchComponent', () => {
  let component: RandommatchComponent;
  let fixture: ComponentFixture<RandommatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RandommatchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RandommatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
